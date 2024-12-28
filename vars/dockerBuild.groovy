def call(String dockerHubUsername, String imageName) {
    // Ensure BuildKit is used
    env.DOCKER_BUILDKIT = '1'

    // Specify Dockerfile path explicitly if not in the root
    sh "docker build --build-arg REACT_APP_RAPID_API_KEY=5f428cbba5msh53a1a25add324f7p1a7b6ejsnffcd9bc28d0c -t ${imageName} -f ./Dockerfile ."
    
    // Tag the image with the username and latest tag
    sh "docker tag ${imageName} ${dockerHubUsername}/${imageName}:latest"
   
    // Push the image to Docker Hub
    withDockerRegistry([url: 'https://index.docker.io/v1/', credentialsId: 'docker']) {
        sh "docker push ${dockerHubUsername}/${imageName}:latest"
    }
}
