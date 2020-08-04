package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class UnreservedTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("A011223344998877");

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final UnreservedValue value = new UnreservedValue();
    value.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    value.addContextSpecificData(contextSpecificData);

    final Unreserved unreserved = new Unreserved();
    unreserved.setValue(value);
    unreserved.setTag("91");

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final Unreserved unreserved = new Unreserved();
    unreserved.setTag("91");
    unreserved.setValue(null);

    assertThat(unreserved.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final Unreserved unreserved = new Unreserved();
    unreserved.setTag("91");
    unreserved.setValue(new UnreservedValue());

    assertThat(unreserved.toString(), equalTo(StringUtils.EMPTY));
  }

}
