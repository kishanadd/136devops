job('terraform-server-setup') {
    scm {
        git('https://raghudevops36:devops321@gitlab.com/batch36/terraform.git')
    }
    steps {
        shell('''cd server-1
terraform init
''')
    }
}

freeStyleJob('terraform-stack-setup') {
  scm {
    git {
      branches('*/master')
      remote {
        url('https://gitlab.com/batch36/terraform.git')
        credentials('dbe25c12-5564-4f14-b9fa-a5de080ec5c5')
      }
    }
  }
  steps {
    shell('''cd stack-1
    terraform init
    ''')
  }
}

folder('STUDENT-PROJ')

pipelineJob('STUDENT-PROJ/CI-Student-App-Dev') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://gitlab.com/batch36/jenkins-jobs.git')
            'credentialsId'('dbe25c12-5564-4f14-b9fa-a5de080ec5c5')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/master')
          }
        }
      }
      'scriptPath'('pipeline-jobs/cicd.groovy')
      'lightweight'(true)
    }
  }
}


folder('STUDENT-PROJ/Docker')

pipelineJob('STUDENT-PROJ/Docker/APP-UI-RELEASE') {
  parameters {
        stringParam('RELEASE_VERSION', '', 'RELEASE_VERSION')
    }
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://gitlab.com/batch36/jenkins-jobs.git')
            'credentialsId'('dbe25c12-5564-4f14-b9fa-a5de080ec5c5')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/master')
          }
        }
      }
      'scriptPath'('pipeline-jobs/kubernetes/appui-release.Jenkinsfile')
      'lightweight'(true)
    }
  }
}