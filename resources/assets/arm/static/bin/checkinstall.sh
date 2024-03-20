#!/system/bin/sh

CHROOT_DIR=$1

export PATH="$1/files/bin:$PATH"
export EXTRACT_UNSAFE_SYMLINKS=1

busybox mount -o remount,exec,suid,dev,rw /data

is_mounted() {

    if $(busybox grep -q "ANDRAX" /proc/mounts); then
        return 0
    else
        return 1
    fi

}

if ! is_mounted; then

    if busybox [[ -e ${CHROOT_DIR}/tmpsystem/usr/bin/zsh ]]; then

        busybox mount -o bind "${CHROOT_DIR}/tmpsystem" "${CHROOT_DIR}/ANDRAX"
        busybox mount -o remount,exec,suid,dev "${CHROOT_DIR}/ANDRAX" "${CHROOT_DIR}/ANDRAX"

        cd ${CHROOT_DIR}/ANDRAX

        busybox mkdir proc
        busybox mkdir dev
        busybox mkdir sys

        if busybox [[ ! -e "/dev/fd" || ! -e "/dev/stdin" || ! -e "/dev/stdout" || ! -e "/dev/stderr" ]]; then
                busybox [[ -e "/dev/fd" ]] || busybox ln -s /proc/self/fd /dev/
                busybox [[ -e "/dev/stdin" ]] || busybox ln -s /proc/self/fd/0 /dev/stdin
                busybox [[ -e "/dev/stdout" ]] || busybox ln -s /proc/self/fd/1 /dev/stdout
                busybox [[ -e "/dev/stderr" ]] || busybox ln -s /proc/self/fd/2 /dev/stderr
        fi

        if busybox [[ ! -e "/dev/tty0" ]]; then

            busybox ln -s /dev/tty /dev/tty0

        fi

        if busybox [[ ! -e "/dev/net/tun" ]]; then

            busybox [[ -d "/dev/net" ]] || busybox mkdir -p /dev/net
            busybox mknod /dev/net/tun c 10 200

        fi

        busybox mount -t binfmt_misc binfmt_misc /proc/sys/fs/binfmt_misc

        busybox mount --bind /proc proc/
        busybox mount --bind /dev dev/
        busybox mount --bind /sys sys/

        busybox mkdir -p /dev/shm
        busybox mount -o rw,nosuid,nodev,mode=1777 -t tmpfs tmpfs /dev/shm
        busybox mount -o bind /dev/shm dev/shm

        busybox mount -o bind /dev/pts dev/pts

        busybox mkdir ${CHROOT_DIR}/ANDRAX/run/sshd ${CHROOT_DIR}/ANDRAX/var/run/sshd

        busybox mount -o remount,exec,suid,dev "${CHROOT_DIR}/ANDRAX" "${CHROOT_DIR}/ANDRAX"

        exit 0

    else

        $1/files/scripts/bootstrap.sh $1

        if busybox [[ $? -gt 0 ]]; then

            exit 1

        fi

    fi

else

	exit 0

fi
