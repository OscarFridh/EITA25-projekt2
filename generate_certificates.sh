OUTPUT_DIR=$(pwd)/ca
CA_DIR=$OUTPUT_DIR
SERVER_RESOURCES=$(pwd)/server/resources
CLIENT_RESOURCES=$(pwd)/client/resources

recreate_folder() {
  if [ -d "$1" ]; then rm -Rf "$1"; fi
  mkdir "$1"
}

create_ca() {
    openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -subj "/CN=CA" -keyout ca.key -out ca.crt
}

create_truststore() {

    TRUSTSTORE_NAME=$1

    # Use keytool to create a truststore that contains the CA certificate (and nothing else)
    keytool -import -trustcacerts -alias root \
    -file "$CA_DIR/ca.crt" -keystore "$TRUSTSTORE_NAME" \
    -storepass password -keypass password -noprompt
}

create_keystore() {

    KEYSTORE_NAME=$1
    DNAME=$2
    

    # Use keytool to create an end-user keypair that is stored in a (client-side) keystore.
    keytool -genkey -alias myDomain \
    -keyalg RSA -keystore "$KEYSTORE_NAME" \
    -dname "$DNAME" \
    -storepass password -keypass password
    
    # Use keytool to create a Certificate Signing Request (CSR) for the keys created in the previous step.
    keytool -certreq -alias myDomain -keystore "$KEYSTORE_NAME" -file certificate.csr -storepass password -keypass password
    
    # Use OpenSSL to let your CA (from step 1) sign the CSR
    openssl x509 -req -days 360 -in certificate.csr -CA "$CA_DIR/ca.crt" -CAkey "$CA_DIR/ca.key" -CAcreateserial -out certificate.crt -sha256
    
    # Import the certificate chain into your keystore. Note that you should import the CA certificate before you import the signed certificate
    keytool -import -trustcacerts -alias root -file "$CA_DIR/ca.crt" -keystore "$KEYSTORE_NAME" -storepass password -keypass password -noprompt
    keytool -import -trustcacerts -alias myDomain -file certificate.crt -keystore "$KEYSTORE_NAME" -storepass password -keypass password -noprompt
    
    # Use keytool to verify that a certificate chain has been established.
    keytool -list -v -keystore "$KEYSTORE_NAME" -storepass password -keypass password

    # Remove unnecessary files
    rm certificate.csr
    rm certificate.crt
    rm "$CA_DIR/ca.srl"
}

add_client_keystores() {
  cd "$OUTPUT_DIR"
  mkdir -p "$CLIENT_RESOURCES"
  create_truststore clienttruststore
  mv clienttruststore "$CLIENT_RESOURCES/clienttruststore"
  create_keystore clientkeystore "CN=Oscar Fridh (os5614fr-s)/Filip Myhrén (fi8057my-s)/Lucas Edlund (lu6512ed-s)/Nils Stridbeck (ni8280st-s), OU=LTH, O=LTH, L=Lund, ST=SE, C=SE"
  mv clientkeystore "$CLIENT_RESOURCES/clientkeystore"
}

add_server_keystores() {
  cd "$OUTPUT_DIR"
  mkdir -p "$SERVER_RESOURCES"
  create_truststore servertruststore
  mv servertruststore "$SERVER_RESOURCES/servertruststore"
  create_keystore serverkeystore "CN=MyServer, OU=LTH, O=LTH, L=Lund, ST=SE, C=SE"
  mv serverkeystore "$SERVER_RESOURCES/serverkeystore"
}

# Remove existing files to reset
recreate_folder "$OUTPUT_DIR"
cd "$OUTPUT_DIR"

# CA
create_ca

# Clients
add_client_keystores # TODO: Create multiple clients and inject DName

# Server
add_server_keystores
