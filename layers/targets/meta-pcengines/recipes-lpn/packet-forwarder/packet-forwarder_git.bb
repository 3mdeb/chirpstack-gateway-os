DESCRIPTION = "packet forwarder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22af7693d7b76ef0fc76161c4be76c45"

SRC_URI = " \
    git://git@github.com/lpn-plant/packet_forwarder.git;branch=lpnGate;protocol=https; \
"

SRCREV = "fbb65f671f5814089a1d5f2179b8889427b5a2f4"

S = "${WORKDIR}/git"

DEPENDS = "libmpsse libftdi lora-gateway"

RDEPENDS_${PN} = "libmpsse-dev libloragw"

EXTRA_OEMAKE = "GLOBAL_CONF_PATH=/etc/lora/global_conf.json"
TARGET_CFLAGS += "-DNOQSORT_R=1 -Iinc -I${RECIPE_SYSROOT}/${includedir}/lora-gateway/"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/lora_pkt_fwd/lora_pkt_fwd ${D}${bindir}
    install -m 0755 ${S}/lora_pkt_fwd/*.sh ${D}${bindir}
    install -m 0755 ${S}/util_ack/util_ack ${D}${bindir}
    install -m 0755 ${S}/util_sink/util_sink ${D}${bindir}
    install -m 0755 ${S}/util_tx_test/util_tx_test ${D}${bindir}

    install -d ${D}${sysconfdir}/lora
    install -m 0644 ${S}/lora_pkt_fwd/cfg/global_conf.json.PCB_E336.EU868.basic ${D}${sysconfdir}/lora/global_conf.json
}

INSANE_SKIP_${PN} = "dev-deps"
