job("Build & Test & JIB") {
    val image = "gradle:7.0.0-jdk11"
    git("models")

    failOn {
        testFailed {  enabled = true  }
        nonZeroExitCode { enabled = true }
        outOfMemory { enabled = true }
        timeOut { timeOutInMinutes = 10 }
    }

    container(displayName = "Build & Test & JIB", image = image) {
        env["JIB_AUTH_USERNAME"] = Secrets("jib_auth_username")
        env["JIB_AUTH_PASSWORD"] = Secrets("jib_auth_password")
        kotlinScript { api ->
            api.gradle("test")
            api.gradle("jib")
        }
    }
}
