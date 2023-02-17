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
import { getEntities as getShippingDetails } from 'app/entities/shipping-details/shipping-details.reducer';
import { IDarazUsers } from 'app/shared/model/daraz-users.model';
import { getEntities as getDarazUsers } from 'app/entities/daraz-users/daraz-users.reducer';
import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { ShippingStatus } from 'app/shared/model/enumerations/shipping-status.model';
import { getEntity, updateEntity, createEntity, reset } from './order-delivery.reducer';

export const OrderDeliveryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orders = useAppSelector(state => state.orders.entities);
  const shippingDetails = useAppSelector(state => state.shippingDetails.entities);
  const darazUsers = useAppSelector(state => state.darazUsers.entities);
  const orderDeliveryEntity = useAppSelector(state => state.orderDelivery.entity);
  const loading = useAppSelector(state => state.orderDelivery.loading);
  const updating = useAppSelector(state => state.orderDelivery.updating);
  const updateSuccess = useAppSelector(state => state.orderDelivery.updateSuccess);
  const shippingStatusValues = Object.keys(ShippingStatus);

  const handleClose = () => {
    navigate('/order-delivery' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getOrders({}));
    dispatch(getShippingDetails({}));
    dispatch(getDarazUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...orderDeliveryEntity,
      ...values,
      order: orders.find(it => it.id.toString() === values.order.toString()),
      shippingAddress: shippingDetails.find(it => it.id.toString() === values.shippingAddress.toString()),
      deliveryManager: darazUsers.find(it => it.id.toString() === values.deliveryManager.toString()),
      deliveryBoy: darazUsers.find(it => it.id.toString() === values.deliveryBoy.toString()),
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
          shippingStatus: 'Pending',
          ...orderDeliveryEntity,
          order: orderDeliveryEntity?.order?.id,
          shippingAddress: orderDeliveryEntity?.shippingAddress?.id,
          deliveryManager: orderDeliveryEntity?.deliveryManager?.id,
          deliveryBoy: orderDeliveryEntity?.deliveryBoy?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="darazDemoApp.orderDelivery.home.createOrEditLabel" data-cy="OrderDeliveryCreateUpdateHeading">
            Create or edit a Order Delivery
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
                <ValidatedField name="id" required readOnly id="order-delivery-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Delivery Date"
                id="order-delivery-deliveryDate"
                name="deliveryDate"
                data-cy="deliveryDate"
                type="date"
              />
              <ValidatedField
                label="Delivery Charge"
                id="order-delivery-deliveryCharge"
                name="deliveryCharge"
                data-cy="deliveryCharge"
                type="text"
              />
              <ValidatedField
                label="Shipping Status"
                id="order-delivery-shippingStatus"
                name="shippingStatus"
                data-cy="shippingStatus"
                type="select"
              >
                {shippingStatusValues.map(shippingStatus => (
                  <option value={shippingStatus} key={shippingStatus}>
                    {shippingStatus}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="order-delivery-order" name="order" data-cy="order" label="Order" type="select" required>
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
              <ValidatedField
                id="order-delivery-shippingAddress"
                name="shippingAddress"
                data-cy="shippingAddress"
                label="Shipping Address"
                type="select"
                required
              >
                <option value="" key="0" />
                {shippingDetails
                  ? shippingDetails.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <ValidatedField
                id="order-delivery-deliveryManager"
                name="deliveryManager"
                data-cy="deliveryManager"
                label="Delivery Manager"
                type="select"
                required
              >
                <option value="" key="0" />
                {darazUsers
                  ? darazUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <ValidatedField
                id="order-delivery-deliveryBoy"
                name="deliveryBoy"
                data-cy="deliveryBoy"
                label="Delivery Boy"
                type="select"
                required
              >
                <option value="" key="0" />
                {darazUsers
                  ? darazUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order-delivery" replace color="info">
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

export default OrderDeliveryUpdate;
