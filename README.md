# Maven Artifacts Uploader
This is a friendly command line tool for uploading artifacts to Nexus 3.x repository

## Installation

1. Clone the project to your computer

```bash
git clone https://github.com/ronbadur/maven-artifacts-uploader.git
```
2. Run ``` mvn install``` command in the directory of the project

3. Fill the repository url and repositroy id fields in ```config.properties``` file under ```conf``` directory

4. Add the direcotry to ``` bin ``` folder to the PATH enviroment variable

5. Add ```<server>``` tag to your maven ```settings.xml``` file, for example:
```bash
<servers>
   <server>
      <id>nexus</id>
      <username>admin</username>
      <password>admin123</password>
    </server>
 </servers>
```
## Usage

For uploading all the artifacts in specific directory on your computer
```bash
mvnUploader -d path/to/your/artifacts
```
To get all the options that available 
```bash
mvnUploader -h
```

## Contributing
By all means! If you have a fix or an improvement, pull requests are welcome. We are also happy to get any feedback.

