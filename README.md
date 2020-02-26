# EITA25-projekt2

[Project description](https://www.eit.lth.se/fileadmin/eit/courses/eita25/proj2/project_2.pdf)

1: Clone the project and generate resources (keystores and truststores)
````
git clone https://github.com/OscarFridh/EITA25-projekt2
cd EITA25-projekt2
sh ./generate_certificates.sh
````

2: Open the project (EITA25-projekt2) in [IntelliJ IDEA](https://www.jetbrains.com/idea/)

There are two modules, one for the server and one for the client.
They each have their own source code and resources. Tests will probably also be added soon.


3: Build the project to bundle production code with resources

4: Start the server from one terminal
````
java out/production/server/server 9875
````

4: Start the client from another terminal
````
java out/production/client/client localhost 9875
````

5: Improve the project! :rocket:

Hopefully everything works as expected...