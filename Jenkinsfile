pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Obtener el código fuente del repositorio
                git branch: 'main', url: 'https://github.com/DarioSierra00/employeCompanySpring.git'
            }
        }

        stage('Build') {
            steps {
                // Compilar la aplicación con Maven
                sh 'mvn clean package'
            }
        }


        stage('Deploy') {
            environment {
                // Definir variables de entorno para el despliegue
                TOMCAT_HOME = '/ruta/al/directorio/de/tomcat'
            }
            steps {
                // Copiar el archivo WAR generado al directorio webapps de Tomcat
                sh "cp target/nombre-del-archivo.war $TOMCAT_HOME/webapps/"
            }
        }

        stage('Cleanup') {
            steps {
                // Limpieza de archivos temporales u otros recursos
                sh 'mvn clean'
            }
        }
    }

    post {
        always {
            // Siempre ejecutar esta etapa, independientemente del resultado de las etapas anteriores
            // Por ejemplo, puedes enviar notificaciones por correo electrónico aquí
        }
        success {
            // Acciones a realizar si el pipeline se ejecuta exitosamente
            echo 'Pipeline ejecutado exitosamente!'
        }
        failure {
            // Acciones a realizar si el pipeline falla
            echo 'El pipeline ha fallado, revisa los registros para más detalles.'
        }
    }
}
