DESCRIPTION = "libmpsse"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=868443b59d4fc856cbe697394d0795f0"

SRC_URI = " \
    git://git@github.com/lpn-plant/libmpsse.git;protocol=https; \
"

SRCREV = "09ded60f7bd8bd4be33fa15fc32b893a2fcbd83f"

inherit autotools-brokensep

S = "${WORKDIR}/git/src"

DEPENDS = "libftdi swig-native"

# It requires python2 which is no longer part of OE
EXTRA_OECONF = "--disable-python"

do_configure() {
    autoconf
    oe_runconf
}

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    touch ${S}/libmpsse_dummy_bin
    install -m 0644 ${S}/libmpsse_dummy_bin ${D}${bindir}
    install -d ${D}${libdir}
    install -m 0644 ${S}/libmpsse.so ${D}${libdir}
    install -m 0644 ${S}/libmpsse.a ${D}${libdir}

    install -d ${D}${includedir}
    install -m 0644 ${S}/mpsse.h ${D}${includedir}
}

INSANE_SKIP_${PN}-dev = "dev-elf"
