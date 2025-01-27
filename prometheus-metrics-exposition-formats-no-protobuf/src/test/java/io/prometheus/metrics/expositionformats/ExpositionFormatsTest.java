/*
 * Copyright © 2024 XDEV Software (https://xdev.software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prometheus.metrics.expositionformats;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import io.prometheus.metrics.config.ExporterProperties;


class ExpositionFormatsTest
{
	static final String OPENMETRICS_HEADER = "application/openmetrics-text";
	static final String PROTOBUF_HEADER =
		"application/vnd.google.protobuf,proto=io.prometheus.client.MetricFamily";
	
	@Test
	void checkFunctionality()
	{
		final ExpositionFormats expositionFormats = assertDoesNotThrow(() ->
			ExpositionFormats.init(ExporterProperties.builder().build()));
		
		assertAll(
			() -> assertNull(expositionFormats.getPrometheusProtobufWriter()),
			() -> assertNotNull(expositionFormats.getOpenMetricsTextFormatWriter()),
			() -> assertNotNull(expositionFormats.getPrometheusTextFormatWriter()),
			() -> assertEquals(
				expositionFormats.getOpenMetricsTextFormatWriter(),
				expositionFormats.findWriter(OPENMETRICS_HEADER)),
			() -> assertEquals(
				expositionFormats.getPrometheusTextFormatWriter(),
				expositionFormats.findWriter("")),
			() -> assertFalse(expositionFormats.findWriter(PROTOBUF_HEADER) instanceof PrometheusProtobufWriter)
		);
	}
}
