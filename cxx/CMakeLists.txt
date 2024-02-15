cmake_minimum_required(VERSION 3.27)
project(cxx)

set(CMAKE_CXX_STANDARD 17)

include_directories("/usr/lib/jvm/default-java/include")
include_directories("/usr/lib/jvm/default-java/include/linux")

include_directories("include")

add_library(alde-core SHARED
        src/core/JCore.cpp)

add_library(alde-framework SHARED
        src/framework/JFramework.cpp)

add_library(bc100dev-commons SHARED
        include/commons/ConsoleSize.h src/commons/ConsoleSize.cpp
        include/commons/ExceptionHandle.h src/commons/ExceptionHandle.cpp
        include/commons/UserIO.h src/commons/UserIO.cpp)