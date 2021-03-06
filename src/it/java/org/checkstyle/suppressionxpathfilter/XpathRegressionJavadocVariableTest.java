////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2018 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package org.checkstyle.suppressionxpathfilter;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocVariableCheck;

public class XpathRegressionJavadocVariableTest extends AbstractXpathRegressionTest {

    @Test
    public void testOne() throws Exception {
        final String checkName = JavadocVariableCheck.class.getSimpleName();
        final File fileToProcess =
                new File(getPath(checkName,
                        "SuppressionXpathRegressionJavadocVariableOne.java"));

        final DefaultConfiguration moduleConfig =
                createModuleConfig(JavadocVariableCheck.class);

        final String[] expectedViolation = {
            "5:5: " + getCheckMessage(JavadocVariableCheck.class,
                JavadocVariableCheck.MSG_JAVADOC_MISSING),
        };

        final List<String> expectedXpathQueries = Arrays.asList(
            "/CLASS_DEF[@text='SuppressionXpathRegressionJavadocVariableOne']/OBJBLOCK"
                + "/VARIABLE_DEF[@text='age']",
            "/CLASS_DEF[@text='SuppressionXpathRegressionJavadocVariableOne']/OBJBLOCK"
                + "/VARIABLE_DEF[@text='age']/MODIFIERS",
            "/CLASS_DEF[@text='SuppressionXpathRegressionJavadocVariableOne']/OBJBLOCK"
                + "/VARIABLE_DEF[@text='age']/MODIFIERS/LITERAL_PRIVATE"
        );

        runVerifications(moduleConfig, checkName, fileToProcess, expectedViolation,
                expectedXpathQueries);
    }

    @Test
    public void testTwo() throws Exception {
        final String checkName = JavadocVariableCheck.class.getSimpleName();
        final File fileToProcess =
                new File(getPath(checkName,
                        "SuppressionXpathRegressionJavadocVariableTwo.java"));

        final DefaultConfiguration moduleConfig =
                createModuleConfig(JavadocVariableCheck.class);

        final String[] expectedViolation = {
            "6:9: " + getCheckMessage(JavadocVariableCheck.class,
                JavadocVariableCheck.MSG_JAVADOC_MISSING),
        };

        final List<String> expectedXpathQueries = Arrays.asList(
            "/CLASS_DEF[@text='SuppressionXpathRegressionJavadocVariableTwo']/OBJBLOCK"
                + "/CLASS_DEF[@text='InnerInner2']/OBJBLOCK/VARIABLE_DEF[@text='fData']",
            "/CLASS_DEF[@text='SuppressionXpathRegressionJavadocVariableTwo']/OBJBLOCK"
                + "/CLASS_DEF[@text='InnerInner2']/OBJBLOCK/VARIABLE_DEF[@text='fData']/MODIFIERS",
            "/CLASS_DEF[@text='SuppressionXpathRegressionJavadocVariableTwo']/OBJBLOCK"
                + "/CLASS_DEF[@text='InnerInner2']/OBJBLOCK/VARIABLE_DEF[@text='fData']/MODIFIERS"
                + "/LITERAL_PUBLIC"
        );

        runVerifications(moduleConfig, checkName, fileToProcess, expectedViolation,
                expectedXpathQueries);
    }
}
