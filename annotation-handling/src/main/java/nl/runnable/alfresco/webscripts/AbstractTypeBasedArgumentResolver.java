/*
Copyright (c) 2012, Runnable
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of Runnable nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package nl.runnable.alfresco.webscripts;

import java.lang.annotation.Annotation;

import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.util.Assert;

/**
 * Convenenience adapter for {@link ArgumentResolver} implementations that rely solely on the parameter type and do not
 * allow parameter annotations.
 * 
 * @author Laurens Fridael
 * 
 * @param <ParameterType>
 */
public abstract class AbstractTypeBasedArgumentResolver<ParameterType> implements
		ArgumentResolver<ParameterType, Annotation> {

	@Override
	public final boolean supports(final Class<?> parameterType, final Class<? extends Annotation> annotationType) {
		return parameterType.equals(getExpectedArgumentType());
	}

	@Override
	public final ParameterType resolveArgument(final Class<?> parameterType, final Annotation parameterAnnotation,
			final String name, final WebScriptRequest request, final WebScriptResponse response) {
		Assert.isTrue(parameterAnnotation == null, "Did not expect a parameter annotation.");
		final Class<?> expectedParameterType = getExpectedArgumentType();
		if (parameterType.equals(expectedParameterType) == false) {
			throw new IllegalArgumentException(String.format("Incorrect parameter type %s, expected type %s",
					parameterType, expectedParameterType));
		}
		return resolveArgument(request, response);
	}

	protected abstract Class<?> getExpectedArgumentType();

	protected abstract ParameterType resolveArgument(WebScriptRequest request, WebScriptResponse response);
}
