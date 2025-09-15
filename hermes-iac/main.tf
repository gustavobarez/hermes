resource "aws_sqs_queue" "main_queue" {
  name = "${var.app_name}-queue"
}

resource "aws_sns_topic" "main_topic" {
  name = "${var.app_name}-topic"
}

resource "aws_sns_topic_subscription" "sqs_target" {
  topic_arn = aws_sns_topic.main_topic.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.main_queue.arn
}

resource "aws_ssm_parameter" "queue_name" {
  name  = "/app/queue-name"
  type  = "String"
  value = aws_sqs_queue.main_queue.name
}

resource "aws_ssm_parameter" "topic_arn" {
  name  = "/app/topic-arn"
  type  = "String"
  value = aws_sns_topic.main_topic.arn
}

resource "aws_sqs_queue_policy" "queue_policy" {
  queue_url = aws_sqs_queue.main_queue.id
  
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Effect = "Allow"
      Principal = "*"
      Action = "sqs:SendMessage"
      Resource = aws_sqs_queue.main_queue.arn
      Condition = {
        ArnEquals = {
          "aws:SourceArn" = aws_sns_topic.main_topic.arn
        }
      }
    }]
  })
}