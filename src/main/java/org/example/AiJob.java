package org.example;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class AiJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> questions = env.fromElements(
                "I'm so excited for the weekend! #TGIF",
                "Ugh, I hate getting stuck in traffic every day. #annoyed",
                "Just got a promotion at work! Feeling on top of the world!",
                "Why do bad things always happen to me? #frustrated",
                "Had an amazing dinner with friends last night. #blessed",
                "Feeling really down today... nothing seems to be going right.",
                "The new movie was fantastic! Highly recommend it.",
                "Can't believe how terrible customer service was today. #disappointed",
                "I'm so proud of my kids for doing so well in school! #proudparent",
                "It's raining again... this weather is the worst. #gloomy",
                "Just finished a great workout, feeling energized!",
                "Why do people have to be so rude? #frustrated",
                "I love my new job, it's everything I hoped for!",
                "Had a rough day at work, just want to relax now.",
                "The concert last night was epic! Best night ever!"
        );

        DataStream<String> answers = questions.map(SentimentAnalysis::getPrediction);
        answers.print();
        env.execute();
    }
}