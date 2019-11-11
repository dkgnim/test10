TARGETS = console-setup resolvconf mountkernfs.sh ufw screen-cleanup hostname.sh apparmor plymouth-log udev keyboard-setup cryptdisks cryptdisks-early iscsid networking open-iscsi hwclock.sh urandom mountdevsubfs.sh checkroot.sh lvm2 checkfs.sh bootmisc.sh mountall.sh checkroot-bootclean.sh mountnfs.sh procps kmod mountnfs-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
iscsid: networking
networking: resolvconf mountkernfs.sh urandom procps
open-iscsi: networking iscsid
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
bootmisc.sh: udev checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
mountnfs.sh: networking
procps: mountkernfs.sh udev
kmod: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh
