#!/usr/bin/env groovy

module = env.MODULE.toLowerCase()

pipeline {

    agent any

    stages {
        stage ("Build Project") {
            steps {
                script {
                    sh "./gradlew -p ${module} clean build"
                }
            }
        }

        stage ("Integration Tests") {
            steps {
                script {
                    echo "Running Integration Tests"
                    sh "./gradlew -p ${module} integrationTest"
                }
            }
        }

        stage("Release") {
            steps {
                script {
                    dir("${module}") {
                        dockerapp = docker.build("arksoncosta/${module}:${env.BUILD_ID}", ".")
                    }
                }
            }
        }

        stage("Docker Push Image") {
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub") {
                        dockerapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }

        stage("Deploy Kubernetes") {
            steps {
                script {
                    dir("deploy/${module}") {
                        try {
                            sh "cat * > deploy.yml"
                        } catch (Exception e) {
                            echo 'Exception occurred: ' + e.toString()
                        }
                        sh "sed -i 's/REPLACEME_TAG/${env.BUILD_ID}/g' deploy.yml"
                        sh "cat deploy.yml"
                        kubernetesDeploy(configs: "deploy.yml", kubeconfigId: "kubeconfig")
                    }
                }
            }
        }
    }

}