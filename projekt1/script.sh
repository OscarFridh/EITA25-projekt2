# Clear existing files to reset
WORKING_DIR=output
if [ -d "$WORKING_DIR" ]; then rm -Rf $WORKING_DIR; fi
mkdir "$WORKING_DIR"
cd $WORKING_DIR

# 1. Create a X.509 CA certificate using OpenSSL, set the CN field to CA and save the private key in a file
openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -subj "/CN=CA" -keyout ca.key -out ca.crt

# 2. Use keytool to create a truststore that contains the CA certificate (and nothing else). Name the file clienttruststore (no file extension) and set the password to password.
keytool -import -trustcacerts -alias root \
-file ca.crt -keystore clienttruststore \
-storepass password -keypass password -noprompt

# 3. Use keytool to create an end-user keypair that is stored in a (client-side) keystore.
keytool -genkey -alias myDomain \
-keyalg RSA -keystore clientkeystore \
-dname "CN=Oscar Fridh (os5614fr-s)/Filip Myhrén (fi8057my-s)/Lucas Edlund (lu6512ed-s)/Nils Stridbeck (ni8280st-s), OU=LTH, O=LTH, L=Lund, ST=SE, C=SE" \
-storepass password -keypass password

# 4. Use keytool to create a Certificate Signing Request (CSR) for the keys created in the previous step.
keytool -certreq -alias myDomain -keystore clientkeystore -file client.csr -storepass password -keypass password

# 5. Use OpenSSL to let your CA (from step 1) sign the CSR
openssl x509 -req -days 360 -in client.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out client.crt -sha256

# 6. Import the certificate chain into your keystore. Note that you should import the CA certificate before you import the signed certificate
keytool -import -trustcacerts -alias root -file ca.crt -keystore clientkeystore -storepass password -keypass password -noprompt
keytool -import -trustcacerts -alias myDomain -file client.crt -keystore clientkeystore -storepass password -keypass password -noprompt

# 7. Use keytool to verify that a certificate chain has been established.
keytool -list -v -keystore clientkeystore -storepass password -keypass password

# 8.

# 9. Follow steps 3 through 7 above to create a server-side keystore. Name the file serverkeystore (no file extension) and set the password to password. Use CN=MyServer for the server certificate.

# 9.3. Use keytool to create an end-user keypair that is stored in a (server-side) keystore.
keytool -genkey -alias myDomain \
-keyalg RSA -keystore serverkeystore \
-dname "CN=MyServer, OU=LTH, O=LTH, L=Lund, ST=SE, C=SE" \
-storepass password -keypass password

# 9.4. Use keytool to create a Certificate Signing Request (CSR) for the keys created in the previous step.
keytool -certreq -alias myDomain -keystore serverkeystore -file server.csr -storepass password -keypass password

# 9.5. Use OpenSSL to let your CA (from step 1) sign the CSR
openssl x509 -req -days 360 -in server.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out server.crt -sha256

# 9.6. Import the certificate chain into your keystore. Note that you should import the CA certificate before you import the signed certificate
keytool -import -trustcacerts -alias root -file ca.crt -keystore serverkeystore -storepass password -keypass password -noprompt
keytool -import -trustcacerts -alias myDomain -file server.crt -keystore serverkeystore -storepass password -keypass password -noprompt

# 9.7. Use keytool to verify that a certificate chain has been established.
keytool -list -v -keystore serverkeystore -storepass password -keypass password

# 10. Create a server-side truststore. Name the file servertruststore (no file extension) and set the password to password.
cp clienttruststore servertruststore

# Copy keystores to part2
cp clientkeystore ../part2/clientkeystore
cp clienttruststore ../part2/clienttruststore
cp serverkeystore ../part2/serverkeystore
cp servertruststore ../part2/servertruststore
