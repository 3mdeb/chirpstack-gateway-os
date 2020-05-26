DESCRIPTION = "lora-gateway"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a2bdef95625509f821ba00460e3ae0eb"

SRC_URI = " \
    git://git@github.com/lpn-plant/lora_gateway.git;branch=lpnGate;protocol=https; \
"

SRCREV = "4ee47be125d3ca93e50e1b81c98ebbe8d7a3ecea"

S = "${WORKDIR}/git"

DEPENDS = "libmpsse libftdi"

RDEPENDS_libloragw += "libmpsse-dev"

TARGET_CFLAGS += "-DLIBFTDI1=1 -Iinc -fPIC"

# USE TO ENABLE DEBUGGING
do_configure() {
    echo "DEBUG_AUX= 1" > ${S}/libloragw/library.cfg
    echo "DEBUG_SPI= 1" >> ${S}/libloragw/library.cfg
    echo "DEBUG_REG= 1" >> ${S}/libloragw/library.cfg
    echo "DEBUG_HAL= 1" >> ${S}/libloragw/library.cfg
    echo "DEBUG_LBT= 1" >> ${S}/libloragw/library.cfg
    echo "DEBUG_GPS= 1" >> ${S}/libloragw/library.cfg
}

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0644 ${S}/libloragw/test* ${D}${bindir}
    install -m 0644 ${S}/util_pkt_logger/util_pkt_logger ${D}${bindir}
    install -m 0644 ${S}/util_spi_stress/util_spi_stress ${D}${bindir}

    install -d ${D}${libdir}
    install -m 0644 ${S}/libloragw/libloragw.* ${D}${libdir}

    install -d ${D}${includedir}/lora-gateway
    install -m 0644 ${S}/libloragw/inc/* ${D}${includedir}/lora-gateway/
}

PACKAGES =+ "libloragw"

FILES_libloragw = "${libdir}/libloragw.*"

INSANE_SKIP_libloragw = "dev-deps"
