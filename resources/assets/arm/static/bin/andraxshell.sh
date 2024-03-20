#!/system/bin/sh

CHROOT_DIR=$HOME

export PATH="$HOME/files/bin:$PATH"

su -c busybox mount -o remount,exec,suid,dev,rw /data

is_mounted() {
    if $(su -c busybox grep -q "ANDRAX" /proc/mounts); then
        return 0
    else
        return 1
    fi
}

if is_mounted; then

    if su -c busybox [[ -e ${CHROOT_DIR}/tmpsystem/usr/bin/zsh ]]; then

        if busybox [[ $# -gt 0 ]]; then

            unset TMP TEMP TMPDIR LD_PRELOAD LD_DEBUG
            su -c busybox chroot ${CHROOT_DIR}/ANDRAX /bin/su - andrax -c "$@"

            exit 0

        else

            unset TMP TEMP TMPDIR LD_PRELOAD LD_DEBUG
            su -c busybox chroot ${CHROOT_DIR}/ANDRAX /bin/su - andrax

            exit 0

        fi

    else

    busybox printf "\n\nATTENTION\n\nINSTALL ANDRAX CORE!!!\n\nOpen the ANDRAX interface to INSTALL IT!\n"
    su -c umount -lf ${CHROOT_DIR}/ANDRAX
    exit 1
    fi

else

	busybox printf "\n\nATTENTION\n\nANDRAX CORE NOT MOUNTED!\n\nOR NOT INSTALLED!!!\n\nOpen the ANDRAX interface to INSTALL IT!\n"

	exit 1

fi
