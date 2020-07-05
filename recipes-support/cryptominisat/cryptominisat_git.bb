DESCRIPTION = "CryptoMiniSat is an advanced incremental SAT solver."
HOMEPAGE = " https://www.msoos.org"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=7cdd497f1ddb031d004fad321c45d8df"
SRC_URI = "git://github.com/msoos/cryptominisat;protocol=https"
SRCREV = "0544260ae1d4338c260930dce8a6cc0512d3e1fb"

S = "${WORKDIR}/git"

PV = "5.7.1+git${SRCPV}"

inherit cmake python3native

EXTRA_OECMAKE = "-DENABLE_PYTHON_INTERFACE=OFF -DNOM4RI=ON -DONLY_SIMPLE=ON"

DEPENDS = " \
        boost \
        "

# Work around the following cross-compiling error:
# | -- Performing Test HAVE__FPU_SETCW
# | CMake Error: TRY_RUN() invoked in cross-compiling mode, please set the following cache variables appropriately:
# |    HAVE__FPU_SETCW_EXITCODE (advanced)
# |    HAVE__FPU_SETCW_EXITCODE__TRYRUN_OUTPUT (advanced)
cmake_do_generate_toolchain_file_append() {
	cat >> ${WORKDIR}/toolchain.cmake <<EOF
set(HAVE__FPU_SETCW FALSE CACHE INTERNAL "" FORCE)
EOF
}

BBCLASSEXTEND = "native nativesdk"
