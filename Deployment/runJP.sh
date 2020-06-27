
# JPackage script

# Variables
output=../Executable/JPackage 
project=JCalc

# Delete previous application image
rm -rf $output

# Define function to create application image
createApp() {
	jpackage \
		--type app-image \
		--main-class main.Entry \
		--main-jar $project.jar \
		--name $project \
		--app-version 1.0 \
		--description "" \
		--icon JCIcon.ico \
		--vendor "David" \
		--input ../Project/dist \
		--dest $output \
		--verbose \
		> JPackageLog.txt
}

# Create application image
if createApp ; then

	# If successful:
	# Notify
	echo ""
	echo "Successfully made application image!"
	
	# Display size
	echo ""
	echo "Size of application image:"
	du -h ../Executable/JPackage | sort -rh | head -1
else 
	# If there was an error:
	# Notify
	echo ""
	echo "Error encountered! See verbose output:"
	
	# Show verbose output
	echo ""
    cat JPackageLog.txt
fi
	
