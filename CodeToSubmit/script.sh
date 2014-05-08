#!/bin/sh
file=$1
awk 'BEGIN {
FS=","
}
{
print $1 " " $(NF-1);
}' $file > WithQuotes
