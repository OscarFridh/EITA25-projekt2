export OUTPUT_DIR=$(pwd)/certificates
export CA_DIR=$OUTPUT_DIR

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
}

# Clear existing files to reset
if [ -d "$OUTPUT_DIR" ]; then rm -Rf "$OUTPUT_DIR"; fi
mkdir "$OUTPUT_DIR"
cd "$OUTPUT_DIR"

# CA
create_ca

# Client
create_truststore clienttruststore
create_keystore clientkeystore "CN=Oscar Fridh (os5614fr-s)/Filip Myhrén (fi8057my-s)/Lucas Edlund (lu6512ed-s)/Nils Stridbeck (ni8280st-s), OU=LTH, O=LTH, L=Lund, ST=SE, C=SE"

# Server
create_truststore servertruststore
create_keystore serverkeystore "CN=MyServer, OU=LTH, O=LTH, L=Lund, ST=SE, C=SE"
