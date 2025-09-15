output "queue_name" {
  value = aws_sqs_queue.main_queue.name
}

output "queue_url" {
  value = aws_sqs_queue.main_queue.url
}

output "topic_arn" {
  value = aws_sns_topic.main_topic.arn
}