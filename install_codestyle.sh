#!/bin/bash
# Installs Schibsted's IntelliJ configs into your user configs.

echo "Installing Schibsted IntelliJ configs..."

CONFIGS="./config"

for i in $HOME/Library/Preferences/IntelliJIdea*  \
         $HOME/Library/Preferences/IdeaIC*        \
         $HOME/Library/Preferences/AndroidStudio* \
         $HOME/.IntelliJIdea*/config              \
         $HOME/.IdeaIC*/config                    \
         $HOME/.AndroidStudio*/config
do
  if [[ -d $i ]]; then

    # Install codestyles
    mkdir -p $i/codestyles
    cp -frv "$CONFIGS/checkstyle/Android.xml" $i/codestyles
    cp -frv "$CONFIGS/checkstyle/Java.xml" $i/codestyles

  fi
done

echo "Done."
echo ""
echo "Restart IntelliJ and/or AndroidStudio, go to preferences, and apply 'SchibstedAndroid' or 'SchibstedJava' scheme as check style preference."