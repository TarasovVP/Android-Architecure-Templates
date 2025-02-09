//
//  ContentView.swift
//  architecturetemplates
//
//  Created by Vladimir Tarasov on 14.04.2024.
//

import SwiftUI
import mobile

struct ComposeView: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

}

struct ContentView: View {

    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard)
    }
}
