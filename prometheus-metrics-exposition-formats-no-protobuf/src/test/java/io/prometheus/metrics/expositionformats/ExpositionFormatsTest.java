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
