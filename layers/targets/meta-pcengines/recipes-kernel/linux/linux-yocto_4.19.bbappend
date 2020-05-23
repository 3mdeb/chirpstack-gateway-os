FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Needed for the SX1302.
KERNEL_MODULE_AUTOLOAD += "i2c-dev"

SRC_URI += " \
    file://chirpstack-requirements.cfg \
    file://spidev.cfg \
"
