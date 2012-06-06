#!/bin/sh
# ADempiere Server Stop script

if [ $IDEMPIERE_HOME ]; then
  cd $IDEMPIERE_HOME/utils
fi

. ./myEnvironment.sh Server
echo Adempiere Server Stop - $IDEMPIERE_HOME \($ADEMPIERE_DB_NAME\)

JBOSS_LIB=$JBOSS_HOME/lib
export JBOSS_LIB
JBOSS_SERVERLIB=$JBOSS_HOME/server/adempiere/lib
export JBOSS_SERVERLIB
JBOSS_CLASSPATH=$IDEMPIERE_HOME/lib/jboss.jar:$JBOSS_LIB/jboss-system.jar:
export JBOSS_CLASSPATH

echo sh $JBOSS_HOME/bin/shutdown.sh --server=jnp://$ADEMPIERE_APPS_SERVER:$ADEMPIERE_JNP_PORT --shutdown
sh $JBOSS_HOME/bin/shutdown.sh --server=jnp://$ADEMPIERE_APPS_SERVER:$ADEMPIERE_JNP_PORT --shutdown
