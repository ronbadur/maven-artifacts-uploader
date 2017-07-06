# Installation
First you need to clone the project to your computer

```bash
git clone https://github.com/ronbadur/maven-artifacts-uploader.git
```
Add ```<server>``` tag to your maven ```settings.xml``` file, for example:
```bash
<server>
      <id>nexus</id>
      <username>admin</username>
      <password>admin123</password>
</server>
```
Fill the repository url and repositroy id fields in ```config.properties``` file under ```conf``` directory

Last but not the least, add the direcotry to ``` bin ``` folder to the PATH enviroment variable

# Usage

For uploading all the artifacts in specific directory on your computer
```bash
mvnUploader -d path/to/your/artifacts
```
To get all the options that available 
```bash
mvnUploader -h
```
