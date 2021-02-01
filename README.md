# aws-lambda-template-kotlin

This project contains source code and supporting files for a serverless application that you can deploy with the SAM CLI. It includes the following files and folders.

- AwsLambdaTemplate/src/main - Code for the application's Lambda function.
- events - Invocation events that you can use to invoke the function.
- AwsLambdaTemplate/src/test - Unit tests for the application code. 
- template.yaml - A template that defines the application's AWS resources.

## Template usage
- Initializes a new SAM project using custom template with guide
```bash
your-folder-name$ sam init
Which template source would you like to use?
        1 - AWS Quick Start Templates
        2 - Custom Template Location
Choice: 2
Template location (git, mercurial, http(s), zip, path): git@github.com:leomindez/aws-lambda-template-kotlin.git
```
- Initializes a new SAM project using custom template in a Git/Mercurial repository
 ```bash
# gh being expanded to github url
$ sam init --location git@github.com:leomindez/aws-lambda-template-kotlin.git
```

See the [AWS SAM developer guide](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/what-is-sam.html) for an introduction to SAM specification, the SAM CLI, and serverless application concepts.

## Deploy the sample application

The Serverless Application Model Command Line Interface (SAM CLI) is an extension of the AWS CLI that adds functionality for building and testing Lambda applications. It uses Docker to run your functions in an Amazon Linux environment that matches Lambda. It can also emulate your application's build environment.

To use the SAM CLI, you need the following tools.

* AWS CLI - [Install the AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html) and [configure it with your AWS credentials].
* SAM CLI - [Install the SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)
* Java11 - [Install the Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* Docker - [Install Docker community edition](https://hub.docker.com/search/?type=edition&offering=community)

The SAM CLI uses an Amazon S3 bucket to store your application's deployment artifacts. If you don't have a bucket suitable for this purpose, create one. Replace `BUCKET_NAME` in the commands in this section with a unique bucket name.

```bash
sam-app$ aws s3 mb s3://BUCKET_NAME
```

To prepare the application for deployment, use the `sam package` command.

```bash
sam-app$ sam package \
    --output-template-file packaged.yaml \
    --s3-bucket BUCKET_NAME
```

The SAM CLI creates deployment packages, uploads them to the S3 bucket, and creates a new version of the template that refers to the artifacts in the bucket. 

To deploy the application, use the `sam deploy` command.

```bash
sam-app$ sam deploy \
    --template-file packaged.yaml \
    --stack-name sam-app \
    --capabilities CAPABILITY_IAM
```

## Use the SAM CLI to build and test locally

Build your application with the `sam build` command.

```bash
sam-app$ sam build
```

The SAM CLI installs dependencies defined in `AwsLamdaTemplate/build.gradle`, creates a deployment package, and saves it in the `.aws-sam/build` folder.

Test a single function by invoking it directly with a test event. An event is a JSON document that represents the input that the function receives from the event source. Test events are included in the `events` folder in this project.

Run functions locally and invoke them with the `sam local invoke` command.

```bash
sam-app$ sam local invoke YourFunctionName --event events/event.json
```

The SAM CLI reads the application template to determine the EventBridge rule pattern and the functions that they invoke as a target. The `Events` property on each function's definition includes the source and detail-type of the types of events that will invoke the function.

```yaml
      Events:
        HelloWorld:
          Type: CloudWatchEvent # More info about CloudWatchEvent Event Source: https://github.com/awslabs/serverless-application-model/blob/master/versions/2016-10-31.md#cloudwatchevent
          Properties:
            Pattern:
              source:
                - aws.ec2
              detail-type:
                - EC2 Instance State-change Notification
```

## Add a resource to your application
The application template uses AWS Serverless Application Model (AWS SAM) to define application resources. AWS SAM is an extension of AWS CloudFormation with a simpler syntax for configuring common serverless application resources such as functions, triggers, and APIs. For resources not included in [the SAM specification](https://github.com/awslabs/serverless-application-model/blob/master/versions/2016-10-31.md), you can use standard [AWS CloudFormation](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-template-resource-type-ref.html) resource types.

## Fetch, tail, and filter Lambda function logs

To simplify troubleshooting, SAM CLI has a command called `sam logs`. `sam logs` lets you fetch logs generated by your deployed Lambda function from the command line. In addition to printing the logs on the terminal, this command has several nifty features to help you quickly find the bug.

`NOTE`: This command works for all AWS Lambda functions; not just the ones you deploy using SAM.

```bash
sam-app$ sam logs -n YourFunctionName --stack-name your-stack-name --tail
```

You can find more information and examples about filtering Lambda function logs in the [SAM CLI Documentation](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-logging.html).

## Unit tests

Tests are defined in the `AwsLambdaTemplate/src/test` folder in this project.

```bash
sam-app$ cd AwsLambdaTemplate
AwsLambdaTemplate$ gradle test
```

## Cleanup

To delete the sample application and the bucket that you created, use the AWS CLI.

```bash
sam-app$ aws cloudformation delete-stack --stack-name your-stack-name
sam-app$ aws s3 rb s3://BUCKET_NAME
```

## Resources

See the [AWS SAM developer guide](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/what-is-sam.html) for an introduction to SAM specification, the SAM CLI, and serverless application concepts.
Next, you can use AWS Serverless Application Repository to deploy ready to use Apps that go beyond hello world samples and learn how authors developed their applications: [AWS Serverless Application Repository main page](https://aws.amazon.com/serverless/serverlessrepo/)
Template for creating AWS Lambda to handle APIGateway Event, DynamoDBStream Event, EventBridge Event
