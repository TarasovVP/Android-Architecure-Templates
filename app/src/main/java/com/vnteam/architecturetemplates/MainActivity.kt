package com.vnteam.architecturetemplates

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.listView)
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.startButton)
    fun onStartButtonClick() {
        progressBar.isVisible = true
        val ormLiteSqliteDBConnector = OrmLiteSqliteDBConnector(this)
        val httpClientConnector = OkHttpClientConnector()
        httpClientConnector.makeHttpUrlConnection({ responseData ->
            val jsonConverter = JsonConverter()
            responseData?.let {
                val forks = jsonConverter.getForkList(responseData)
                ormLiteSqliteDBConnector.insertDataAsync(jsonConverter.forkListToForkDBList(forks), {
                    val forkList = ormLiteSqliteDBConnector.getTransformedForks()
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, forkList.map { it.name })
                    listView.adapter = adapter
                    progressBar.isVisible = false
                    listView.setOnItemClickListener { _, _, position, _ ->
                        Log.e("apiTAG", "MainActivity SUCCESS_SQLITE_CONNECTION setOnItemClickListener position $position")
                        val intent = Intent(this, DetailsActivity::class.java)
                        intent.putExtra(FORK, forkList[position])
                        startActivity(intent)
                    }
                }, { errorText ->
                    Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                })
            }
        }, { errorText ->
            Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        const val DATABASE_NAME = "AndroidArchitectureTemplates"
        const val FORK = "fork"
    }
}