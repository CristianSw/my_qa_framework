#!/bin/bash
# Delete old folder with project in jenkins workspace
sudo rm -rf /my_framework/my_qa_framework/

# Copy project to jenkins workspace
sudo cp -r ~/eclipse-selenium/my_qa_framework/ /my_framework/

# Give to my_qa_framework jenkins user wrights
sudo chown -R jenkins:jenkins /my_framework/my_qa_framework/