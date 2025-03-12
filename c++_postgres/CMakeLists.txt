cmake_minimum_required(VERSION 3.27)
project(c++_postgres)

set(CMAKE_CXX_STANDARD 17)

set(PQXX_ROOT "/opt/homebrew/opt/libpqxx")
set(PQ_ROOT "/opt/homebrew/opt/libpq")

include_directories(${PQXX_ROOT}/include ${PQ_ROOT}/include)

link_directories(${PQXX_ROOT}/lib ${PQ_ROOT}/lib)

add_executable(c++_postgres main.cpp)

target_link_libraries(c++_postgres pqxx pq)