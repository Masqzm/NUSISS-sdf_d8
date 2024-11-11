# Directories
SRC_DIR := src
CLASS_DIR := classes

# Main source files for server and client applications
SERVER_MAIN_SRC := $(SRC_DIR)/sg/edu/nus/iss/baccarat/server/ServerApp.java
CLIENT_MAIN_SRC := $(SRC_DIR)/sg/edu/nus/iss/baccarat/client/ClientApp.java

# Find all Java source files in SRC_DIR including its subdir
SOURCES := $(shell dir /s /b $(SRC_DIR)\*.java)

# Program args
PORT_NO := 12345
HOSTNAME := localhost
TOTAL_DECKS := 4

# Server & client args
SERVER_ARGS := $(PORT_NO) $(TOTAL_DECKS) 
CLIENT_ARGS := $(HOSTNAME):$(PORT_NO)

CLIENT_APP = client.ClientApp
SERVER_APP = server.ServerApp


# Compile & run
run-server: 
	javac -d $(CLASS_DIR) --source-path $(SRC_DIR) $(SERVER_MAIN_SRC)
	java -cp $(CLASS_DIR) sg.edu.nus.iss.baccarat.server.ServerApp $(SERVER_ARGS)

run-client: 
	javac -d $(CLASS_DIR) --source-path $(SRC_DIR) $(CLIENT_MAIN_SRC)
	java -cp $(CLASS_DIR) sg.edu.nus.iss.baccarat.client.ClientApp $(CLIENT_ARGS)


# Clean up the compiled classes
clean:
	del /q $(CLASS_DIR)\*.class


# Usage:
# make run-server
# make run-client
# make clean