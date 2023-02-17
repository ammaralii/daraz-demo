import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOrders } from 'app/shared/model/orders.model';
import { getEntities as getOrders } from 'app/entities/orders/orders.reducer';
import { IShippingDetails } from 'app/shared/model/shipping-details.model';
import { ShippingMethod } from 'app/shared/model/enumerations/shipping-method.model';
import { getEntity, updateEntity, createEntity, reset } from './shipping-details.reducer';

export const ShippingDetailsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orders = useAppSelector(state => state.orders.entities);
  const shippingDetailsEntity = useAppSelector(state => state.shippingDetails.entity);
  const loading = useAppSelector(state => state.shippingDetails.loading);
  const updating = useAppSelector(state => state.shippingDetails.updating);
  const updateSuccess = useAppSelector(state => state.shippingDetails.updateSuccess);
  const shippingMethodValues = Object.keys(ShippingMethod);

  const handleClose = () => {
    navigate('/shipping-details' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getOrders({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...shippingDetailsEntity,
      ...values,
      order: orders.find(it => it.id.toString() === values.order.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          shippingMethod: 'COD',
          ...shippingDetailsEntity,
          order: shippingDetailsEntity?.order?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="darazDemoApp.shippingDetails.home.createOrEditLabel" data-cy="ShippingDetailsCreateUpdateHeading">
            Create or edit a Shipping Details
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="shipping-details-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Shipping Address"
                id="shipping-details-shippingAddress"
                name="shippingAddress"
                data-cy="shippingAddress"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 32, message: 'This field cannot be longer than 32 characters.' },
                }}
              />
              <ValidatedField
                label="Shipping Method"
                id="shipping-details-shippingMethod"
                name="shippingMethod"
                data-cy="shippingMethod"
                type="select"
              >
                {shippingMethodValues.map(shippingMethod => (
                  <option value={shippingMethod} key={shippingMethod}>
                    {shippingMethod}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Estimated Delivery Date"
                id="shipping-details-estimatedDeliveryDate"
                name="estimatedDeliveryDate"
                data-cy="estimatedDeliveryDate"
                type="date"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField id="shipping-details-order" name="order" data-cy="order" label="Order" type="select" required>
                <option value="" key="0" />
                {orders
                  ? orders.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/shipping-details" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ShippingDetailsUpdate;
