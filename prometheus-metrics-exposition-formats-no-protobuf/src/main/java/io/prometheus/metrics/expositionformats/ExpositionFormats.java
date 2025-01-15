package io.prometheus.metrics.expositionformats;

import io.prometheus.metrics.config.ExporterProperties;
import io.prometheus.metrics.config.PrometheusProperties;


/**
 * Fork of original. Disables Protobuf completely
 */
@SuppressWarnings({"checkstyle:FinalClass", "unused"})
public class ExpositionFormats
{
	private final PrometheusTextFormatWriter prometheusTextFormatWriter;
	private final OpenMetricsTextFormatWriter openMetricsTextFormatWriter;
	
	private ExpositionFormats(
		final PrometheusProtobufWriter prometheusProtobufWriter, // Ignored
		final PrometheusTextFormatWriter prometheusTextFormatWriter,
		final OpenMetricsTextFormatWriter openMetricsTextFormatWriter)
	{
		this.prometheusTextFormatWriter = prometheusTextFormatWriter;
		this.openMetricsTextFormatWriter = openMetricsTextFormatWriter;
	}
	
	public static ExpositionFormats init()
	{
		return init(PrometheusProperties.get().getExporterProperties());
	}
	
	public static ExpositionFormats init(final ExporterProperties properties)
	{
		return new ExpositionFormats(
			null,
			new PrometheusTextFormatWriter(properties.getIncludeCreatedTimestamps()),
			new OpenMetricsTextFormatWriter(
				properties.getIncludeCreatedTimestamps(), properties.getExemplarsOnAllMetricTypes()));
	}
	
	public ExpositionFormatWriter findWriter(final String acceptHeader)
	{
		if(this.openMetricsTextFormatWriter.accepts(acceptHeader))
		{
			return this.openMetricsTextFormatWriter;
		}
		return this.prometheusTextFormatWriter;
	}
	
	public PrometheusProtobufWriter getPrometheusProtobufWriter()
	{
		return null;
	}
	
	public PrometheusTextFormatWriter getPrometheusTextFormatWriter()
	{
		return this.prometheusTextFormatWriter;
	}
	
	public OpenMetricsTextFormatWriter getOpenMetricsTextFormatWriter()
	{
		return this.openMetricsTextFormatWriter;
	}
}
