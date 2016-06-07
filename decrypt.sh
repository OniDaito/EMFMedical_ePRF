#!/bin/bash

# Simple bash script to decode the EMF ePRF files using openssl and a few bash commands
# Probably shouldn't use intermediate files for the keys - memory might be a little better

head -c 128 $1 > prfkeyfile
openssl rsautl -oaep -decrypt -inkey emf_medical.pem -in prfkeyfile -out prfkey_decrypted
dd bs=128 skip=1 if=$1 of=prf_plus_iv

head -c 128 prf_plus_iv > prfivfile
openssl rsautl -oaep -decrypt -inkey emf_medical.pem -in prfivfile -out iv_decrypted
dd bs=128 skip=1 if=prf_plus_iv of=prf_encrypted

iv=`cat iv_decrypted`
key=`cat prfkey_decrypted`

echo $iv
echo $key

openssl aes-256-cbc -d -in prf_encrypted -out prf_decrypted.txt -iv $iv -K $key

rm iv_decrypted
rm prfkey_decrypted
rm prf_encrypted
rm prf_plus_iv
rm prfivfile
rm prfkeyfile
