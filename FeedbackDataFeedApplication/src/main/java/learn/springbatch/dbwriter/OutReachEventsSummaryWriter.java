package learn.springbatch.dbwriter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import learn.springbatch.model.OutReachEventsSummary;
import learn.springbatch.repository.OutReachEventsSummaryRepo;

@Component
public class OutReachEventsSummaryWriter implements ItemWriter<OutReachEventsSummary> {
	
	Logger log = LoggerFactory.getLogger(OutReachEventsSummaryWriter.class);

	@Autowired
	OutReachEventsSummaryRepo eventSummaryRepo;

	@Override
	public void write(List<? extends OutReachEventsSummary> eventSummary) throws Exception {
		log.info("Writer method :::: ");
		eventSummary.stream().forEach((summary) ->System.out.println("Event :::"+summary.getEventID()));
		eventSummaryRepo.saveAll(eventSummary);
	}

}
