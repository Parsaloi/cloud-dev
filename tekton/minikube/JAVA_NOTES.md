
## Refactoring Notes

The current YAML declaration deploys a stateless Quarkus application in a Kubernetes cluster with *Node Affinity Rules* and *Pod Topology contraints*.  
However, there are a few areas where it can be imporoved:  

1. *Node Affinity Rules:* The YAML includes an example structure for Node Affinity Rules, but you need to provide specific values for `<node-label-key>`  
and `<node-label-value>`. Ensuring that this values correspond to the ***specific node labels*** in your cluster guarantess Pod allocation to  
your desired target nodes  

2. *Pod Topology Contraints:* Similar to *Node Affinity*, the YAML includes an example structure for Pod Topology constraints. An appropriate *key* based  
on the topology you want to constrain is `kubernetes.io/hostname` <-- *for host-based constraints*  

3. *Logging and Monitoring:* The YAML doesn't include configurations for logging and monitoring. A recommendation is to ***integrate the Quarkus  
application with logging and monitoring solutions*** such as *Promotheus, Fluentd, or Elastic Stack* for better observability  

4. *Configuration and Secrets:* The YAML doesn't cover configurations and secrets management for the Quarkus application. Considering to utilize  
Kubernetes *ConfigMaps* or *Secrets* to manage application configurations separatelty from the image is an appropriate choice


