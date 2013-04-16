package com.kodokux.magedox;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/15
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class SimpleCompletionContributor extends CompletionContributor {

    public SimpleCompletionContributor() {
        final PsiElementPattern
                .Capture<PsiElement> everywhere = PlatformPatterns.psiElement();
        extend(CompletionType.BASIC, everywhere, new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters completionParameters, ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
                completionResultSet.addElement(LookupElementBuilder.create("johnathan"));
            }
        });
    }
}
