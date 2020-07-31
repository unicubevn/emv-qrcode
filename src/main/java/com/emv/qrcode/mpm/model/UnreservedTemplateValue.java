package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.mpm.constants.UnreservedTemplateFieldCodes;
import com.emv.qrcode.parsers.Parser;

import lombok.Getter;

@Getter
public class UnreservedTemplateValue implements Serializable, DrawData {

  private static final long serialVersionUID = -3465559955367881407L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Context Specific Data
  private final List<TagLengthString> contextSpecificData = new LinkedList<>();

  public void setGloballyUniqueIdentifier(final String value) {
    globallyUniqueIdentifier = new TagLengthString(UnreservedTemplateFieldCodes.UNRESERVED_TEMPLATE_ID_GLOBALLY_UNIQUE_IDENTIFIER, value);
  }

  public void addContextSpecificData(final String value) {
    contextSpecificData.add(new TagLengthString(value.substring(0, Parser.ID_WORD_COUNT), value.substring(Parser.ID_WORD_COUNT)));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TagLengthString tagLengthString : contextSpecificData) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    return sb.toString();
  }

  @Override
  public String draw(final DataType type) {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.draw(type)));

    for (final TagLengthString tagLengthString : contextSpecificData) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    return sb.toString();
  }

}
