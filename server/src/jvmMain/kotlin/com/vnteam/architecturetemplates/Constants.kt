package com.vnteam.architecturetemplates

const val HTTP_HOST = "0.0.0.0"
const val HTTP_PORT = 8080
const val DRIVER_CLASS_NAME = "org.postgresql.Driver"
const val JDBC_URL =
    "jdbc:postgresql:///DemoObjectWithOwner?cloudSqlInstance=android-architecture-templates:us-central1:" +
        "android-architecture-templates-db&socketFactory=com.google.cloud.sql.postgres.SocketFactory"
const val DB_USER_NAME = "dbusername"
const val DB_USER_PASSWORD = "yourpassword"

const val MAXIMUM_POOL_SIZE = 5
const val MINIMUM_IDLE = 2
const val IDLE_TIMEOUT = 10000L
const val CONNECTION_TIMEOUT = 30000L
const val MAX_LIFE_TIME = 1800000L
