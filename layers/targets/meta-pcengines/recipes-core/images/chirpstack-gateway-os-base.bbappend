IMAGE_FSTYPES = "ext4.gz wic.gz"
WKS_FILES = "chirpstack-gateway-os.wks"

IMAGE_INSTALL += "connman \
                  connman-client \
                  software-update \
                  packet-forwarder \
                  gateway-config \
                  gateway-id \
"

DISTRO_FEATURES += "wifi"
