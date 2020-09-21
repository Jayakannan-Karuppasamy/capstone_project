package learn.springbatch.dbwriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import learn.springbatch.model.OutReachEventInformation;
import learn.springbatch.repository.OutReachEventInformationRepo;

@Component
public class OutReachEventInformationWriter implements ItemWriter<OutReachEventInformation> {

	@Autowired
	OutReachEventInformationRepo eventInfoRepo;

	@Override
	public void write(List<? extends OutReachEventInformation> eventInformations) throws Exception {
		eventInfoRepo.saveAll(eventInformations);
	}

}
