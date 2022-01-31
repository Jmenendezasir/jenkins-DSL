job('App-Nodejs-Docker-DSL') {
    description('Aplicación Node JS Docker DSL para el curso de Jenkins y run.sh')
    scm {
        git('https://github.com/Jmenendezasir/jenkins-DSL.git', 'master'){ node -> 
            node / gitConfigName('Jmenendezasir')
            node / gitConfigEmail('jmenendezasir@gmail.com')
        }
    }
    triggers {
        githubPush()
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('jmenendezara/jenkins-dsl')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('docker-hub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    shell("bash run.sh")
    }
}