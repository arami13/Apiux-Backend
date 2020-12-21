#!/bin/bash
# Script for Container App Deployment
# @Author Angela Ramirez 
# @Project APIUX
# @Version 1.0
# Name deploy-front.sh
#
# NOTE: You must be logged into Azure command line (az login...) and ACR
# NOTE: If you have any error with credencial please remember to do docker login inside ACR. 
# execute this file -->  bash ./deploy-front-general.sh namespace aplicacion repoResgitryAzure.azurecr.io v1


NS=$1
APP=$2
REGISTRY=$3
TAG=$4

#Set namespace inside de context
kubectl config set-context --current --namespace=$NS
#compile for prod
ng build --prod
#build docker image
docker build -t $APP .
#tag image
docker tag $APP $REGISTRY/$APP:$TAG

#push image (note registry must change for enviroment qa, des, prod..) 
docker push $REGISTRY/$APP:$TAG
#configure AKS resources
kubectl apply -f yamls/configmap.yaml
kubectl apply -f yamls/service.yaml
kubectl apply -f yamls/deployment.yaml
kubectl apply -f yamls/horizontalPodAuto.yaml
kubectl apply -f yamls/oficinasDashboard-ingress-des.yaml
