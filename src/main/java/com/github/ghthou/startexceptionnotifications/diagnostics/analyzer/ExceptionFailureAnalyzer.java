package com.github.ghthou.startexceptionnotifications.diagnostics.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.core.Ordered;

public class ExceptionFailureAnalyzer extends AbstractFailureAnalyzer<Exception> implements Ordered {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, Exception cause) {
        return new FailureAnalysis(cause.getMessage(), cause.getMessage(), cause);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
