#!/system/bin/sh

busybox mount -o remount,exec,suid,dev,rw /data
exit 0