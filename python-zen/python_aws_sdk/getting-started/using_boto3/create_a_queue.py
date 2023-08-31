import boto3

# Get the service resource
sqs = boto3.resource('sqs')

# Create the queue. Thi returns a SQS.Queue instance
queue = sqs.create_queue(QueueName='test', Attributes={'DelaySeconds': '5'})

# you can now acces identifiers and attributes
print(queue.url)
print(queue.attributes.get('DelaySeconds'))

# Find reference for SQS.ServiceResource.create_queue():
# <https://boto3.amazonaws.com/v1/documentation/api/latest/reference/services/sqs/service-resource/create_queue.html#SQS.ServiceResource.create_queue>

# > WARNING/ The above may throw an exception if you already have a queue named test 
