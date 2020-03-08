# EITA25-projekt2

[Project description](https://www.eit.lth.se/fileadmin/eit/courses/eita25/proj2/project_2.pdf)

## Clone from Github
Clone the project and generate resources (keystores and truststores)
````
git clone https://github.com/OscarFridh/EITA25-projekt2
cd EITA25-projekt2
sh ./generate_certificates.sh
````

# Demonstration


### Start the server
```
cd production/server
java server 9876
```

### Start a client

```
cd production/client
java client localhost 9876
```

### Login

For demonstration purposes, the following users have been registered in the server.

* {id: 1, role: Patient}
* {id: 2, role: Patient}
* {id: 3, role: Doctor, division: 1}
* {id: 4, role: Doctor, division: 2}
* {id: 5, role: Nurse, division: 1}
* {id: 6, role: Nurse, division: 2}
* {id: 7, role: Government}

Login with the id as username on the client.
For demonstration purposes they each have the same password; password

### Commands

Create a new medical reccord for a patient with id 1, assigned to the nurse with id 5 (and the logged in doctor), and the text "Medical data"
```
create 1 5 Medical data
```

Update text of the the medical reccord with id 1 to be "Updated medical data"
```
update 1 Updated medical data
```

Read text of the the medical reccord with id 1 to be "Updated medical data"
```
read 1
```

Delete the medical reccord with id 1
```
delete 1
```

For more details on how the commands, access control, and audit log works, please see the attached unit tests. 

### Github

The project is available on Github.
https://github.com/OscarFridh/EITA25-projekt2