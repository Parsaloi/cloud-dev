
#### 🙃

In the second YAML delcaration, the `weight: 100` attribute within the `preferredDuringSchedulingIgnoredDuringExecution` section specifies the  
scheduling preference for the *Node Anti-Affinity rule*

In Kubernetes, the `weight` field is used to indicate the relative importance or priority of a scheduling preference. It allows you to assign different  
weights to *multiple preferences*, indicating which preference should be given higher priority during the scheduling process

Here:  
```YAML
affinity:
  nodeAffinity:
    preferredDuringSchedulingIgnoredDuringExecution:
      - weight: 100
        preference:
          matchExpressions:
            - key: kubernetes.io/hostname
              operator: NotIn
              values:
                - <node-name-to-avoid>
```

..the `weight` is set to `100`, indicating that the preference defined by the `matchExpressions` should be highly prioritized during scheduling.  
This means that that the scheduler will try to avoid placing replicas of the Quarkus app on the nodes specified in the `values` list to a greater extent  
compared to other preferences  

By assigning a higher weight to a prefernce, you can influence the scheduler to prioritize that preference while making scheduling decisions. This  
allows for a more fine-grained control over how pods are distributed across the nodes in the cluster

> Official k8s NOTES:   
> > **Node affinity weight** <-- You can specify a `weight` between 1 and 100 for instance of the `preferredDuringSchedulingIgnoredDuringExecution`  
affinity type. When the scheduler finds nodes that meet all the other scheduling requirements of the Pod, the scheduler iterates through every preferred  
every preffered rule that the node satisfies and adds the value of the `weight` for that expression to a sum  

The final sum is added to the score of the other priority funtions for the Node. Nodes with the highest total score are prioritized when the scheduler  
makes a scheduling decision for the Pod:

For example, consider the following Pod spec:  
```YAML
apiVersion: v1
kind: Pod
metadata:
  name: with-affinity-anti-affinity
spec:
  affinity:
    nodeAffinity:
      requiredDuringSchedulingIgnoredDuringExecution:
        nodeSelectorTerms:
          - matchExpressions:
            - key: kubernetes.io/os
              operator: In
              values:
        preferredDuringSchedulingIgnoredDuringExecution:
        - weight: 1
          preference:
            matchExpressions:
            - key: label-1
              operator: In
              values:
              - key-1
        - weight: 50
          preference:
            matchExpressions:
            - key: label-2
              operator: In
              values:
              - key-2
  containers:
  - name: with-node-affinity
    image: registry.k8s.io/pause:2.0
```

If there are two possible nodes that much the `preferredDuringSchedulingIgnoredDuringExecution` rule, one with the `label: key-1` and another with the  
`label-2: key-2` label, the scheduler considers the `weight` of each node and adds the weight to the other scores for that node, and schedules the Pod  
onto the node with the highest final score

##### Reference Notes

`nodeSelector` is the *simplest recommended* form of node selection constraint. You can add the `nodeSelector` field to your Pod specification and specify  
the **node labels** you want the target node to have. Kubernetes only schedules the Pod onto nodes that have each of the labels you specify, for example:  
Given:  
```bash
kubectl label nodes <my-node> disktype=ssd
```  

then:  
```YAML
apiVersion: v1
kind: Pod
metadata:
  name: nginx
  labels:
    env: test
spec:
  containers:
  - name: nginx
    image: nginx
    imagePulPolicy: IfNotPresent
  nodeSelector:
    disktype: ssd
```

##### ...
Some of the benefits of affinity and anti-affinity include:  
- The affinity/anti-affinity language is more expressive. `nodeSelector` only selects nodes woth all the *specified labels*. Affinity/anti-affinity  
gives you more control over the selection logic  
- You can indicate that a rule is *soft* or *preferred*, so that the scheduler still schedules the Pod even if it can't find a matching node  
- You can constrain a Pod using labels on other Pods running on the node (or other topological domain), instead of just node labels, which allows to  
*define rules* for which Pods can be co-located on a node

The affinity feature consists of two types of affinity:  
- *Node affinity* funtions like the `nodeSelector` field but is more expressive and allows you to specify soft rules.  
- *Inter-pod affinity/ anti-affinity* allows you to contrain Pods against labels on other Pods
- 
