package com.github.ghthou.startexceptionhandle.diagnostics.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class ExceptionFailureAnalyzer extends AbstractFailureAnalyzer<Exception> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, Exception cause) {
        return new FailureAnalysis(cause.getMessage(), cause.getMessage(), cause);
    }
}
